package nevg.card.Service;

import nevg.card.Models.GuestDTO;
import nevg.card.Models.MailSendDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MailSendServiceImpl implements MailSendService {
    @Value("${mailjet.api-key}")
    private String apiKey;

    @Value("${mailjet.secret-key}")
    private String secretKey;

    @Value("${mailjet.from-email}")
    private String fromEmail;

    @Value("${mailjet.from-name}")
    private String fromName;

    @Override
    public String sendEmailInvate(GuestDTO guestDTO) {
        RestTemplate restTemplate = new RestTemplate();

        MailSendDTO mailSendDTO = new MailSendDTO();

        mailSendDTO.setToEmail(guestDTO.getEmail());
        mailSendDTO.setToName(guestDTO.getMainFirstName() + " " + guestDTO.getMainLastName());
        mailSendDTO.setSubject("Приета покана за кръщението на Антон");

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(apiKey, secretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = mapMailjetCardEmail(mailSendDTO, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "https://api.mailjet.com/v3.1/send",
                    request,
                    String.class
            );

            String responseBody = response.getBody();

            if (response.getStatusCode().is2xxSuccessful()
                    && responseBody != null
                    && responseBody.contains("\"Status\":\"success\"")) {
                return "SUCCESS";
            }

            return "ERROR";

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

//    private HttpEntity<Object> mapMailjetCardEmail(MailSendDTO mailSendDTO, HttpHeaders headers) {
//        String textContent = (mailSendDTO.getTextContent() != null && !mailSendDTO.getTextContent().isBlank())
//                ? mailSendDTO.getTextContent()
//                : "Благодарим ти " + mailSendDTO.getToName() + " че приехте нашата покана!" +
//                "Очакваме ви на 31.05.2026г.";
//
//        String htmlContent = (mailSendDTO.getHtmlContent() != null && !mailSendDTO.getHtmlContent().isBlank())
//                ? mailSendDTO.getHtmlContent()
//                : "<p><strong>Благодарим ти " + mailSendDTO.getToName() + " че приехте нашата покана!</strong></p>" +
//                "<p>Ако имате някакви въпроси позвънете на 0896450701 или 0899524251</p>" +
//                "<p>Очакваме ви на 31.05.2026г.</p>";
//
//        Map<String, Object> body = Map.of(
//                "Messages", List.of(
//                        Map.of(
//                                "From", Map.of(
//                                        "Email", fromEmail,
//                                        "Name", fromName
//                                ),
//                                "To", List.of(
//                                        Map.of(
//                                                "Email", mailSendDTO.getToEmail(),
//                                                "Name", mailSendDTO.getToName()
//                                        )
//                                ),
//                                "ReplyTo", Map.of(
//                                        "Email", fromEmail,
//                                        "Name", fromName
//                                ),
//                                "Subject", mailSendDTO.getSubject(),
//                                "TextPart", textContent,
//                                "HTMLPart", "<div style='font-family:Arial,sans-serif;font-size:16px;line-height:1.6;color:#333;'>" + htmlContent + "</div>",
//                                "TrackOpens", "disabled",   // ← добави това
//                                "TrackClicks", "disabled"   // ← и това
//                        )
//                )
//        );
//
//        return new HttpEntity<>(body, headers);
//    }
private HttpEntity<Object> mapMailjetCardEmail(MailSendDTO mailSendDTO, HttpHeaders headers) {

    String htmlContent = (mailSendDTO.getHtmlContent() != null && !mailSendDTO.getHtmlContent().isBlank())
            ? mailSendDTO.getHtmlContent()
            : "<p><strong>Благодарим ти, " + mailSendDTO.getToName() + ", че прие нашата покана!</strong></p>" +
            "<p>Ако имате някакви въпроси позвънете на 0896450701 или 0899524251</p>" +
            "<p><strong>Очакваме ви на 31.05.2026 г.</strong></p>";

    String plainText = (mailSendDTO.getTextContent() != null && !mailSendDTO.getTextContent().isBlank())
            ? mailSendDTO.getTextContent()
            : htmlContent
            .replaceAll("<[^>]*>", " ")
            .replaceAll("&nbsp;", " ")
            .replaceAll("&amp;", "&")
            .replaceAll("\\s+", " ")
            .trim();

    Map<String, Object> body = Map.of(
            "Messages", List.of(
                    Map.of(
                            "From", Map.of("Email", fromEmail, "Name", fromName),
                            "Sender", Map.of("Email", fromEmail, "Name", fromName),
                            "To", List.of(Map.of("Email", mailSendDTO.getToEmail(), "Name", mailSendDTO.getToName())),
                            "ReplyTo", Map.of("Email", fromEmail, "Name", fromName),
                            "Subject", mailSendDTO.getSubject(),
                            "TextPart", plainText,
                            "HTMLPart", wrapHtml(htmlContent),
                            "TrackOpens", "disabled",
                            "TrackClicks", "disabled"
                    )
            )
    );

    return new HttpEntity<>(body, headers);
}

    private String wrapHtml(String content) {
        String safeContent = (content != null) ? content : "";
        return """
        <div style='font-family:Arial,sans-serif; font-size:16px; line-height:1.6; color:#333; max-width:600px; margin:0 auto; padding:20px;'>
            <p>%s</p>
            <hr style='border:none; border-top:1px solid #eee; margin:20px 0;'/>
            <p style='font-size:12px; color:#999;'>
                Това съобщение е изпратено от Николай<br/>
                При въпроси: 0896450701 или 0899524251
            </p>
        </div>
    """.formatted(safeContent);
    }
}
