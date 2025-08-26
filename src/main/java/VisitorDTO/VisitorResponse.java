package VisitorDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorResponse {
    private Long id;
    private String message;
    private String unitNo;
    private String fileName;
    private String imagePath;

}
