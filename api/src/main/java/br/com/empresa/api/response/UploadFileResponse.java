package br.com.empresa.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
