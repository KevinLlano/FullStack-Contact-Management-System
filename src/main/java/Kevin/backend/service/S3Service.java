package Kevin.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.cloudfront.domain:}")
    private String cloudFrontDomain;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(MultipartFile file, String contactId) throws IOException {
        // Generate unique filename
        String fileName = generateFileName(file.getOriginalFilename(), contactId);

        // Upload to S3
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .acl(ObjectCannedACL.PRIVATE) // Secured uploads
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        // Return URL (CloudFront if configured, otherwise S3)
        return getImageUrl(fileName);
    }

    public String generatePresignedUrl(String fileName, int expirationMinutes) {
        // Generate presigned URL for secure access
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        // Implementation using presigned URL, this is a placeholder url
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, fileName);
    }

    public void deleteImage(String fileName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.deleteObject(deleteObjectRequest);
    }

    private String generateFileName(String originalFilename, String contactId) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return String.format("contacts/%s/%s%s", contactId, UUID.randomUUID().toString(), extension);
    }

    private String getImageUrl(String fileName) {
        if (cloudFrontDomain != null && !cloudFrontDomain.isEmpty()) {
            return String.format("https://%s/%s", cloudFrontDomain, fileName);
        }

        // dummy url for S3 bucket
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, fileName);
    }
}
