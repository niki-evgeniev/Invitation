package nevg.card.Controller;

import nevg.card.Models.GuestDTO;
import nevg.card.Models.MailSendDTO;
import nevg.card.Service.GuestService;
import nevg.card.Service.MailSendService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
public class AddGuestController {

    private final GuestService guestService;
    private final MailSendService mailSendService;

    public AddGuestController(GuestService guestService, MailSendService mailSendService) {
        this.guestService = guestService;
        this.mailSendService = mailSendService;
    }

    @PostMapping("/addGuest")
    ResponseEntity<?> addGuest(@RequestBody GuestDTO guestDTO) {
        String message = guestService.addGuest(guestDTO);
        if (message.equals("error") || message.equals("Email exist")) {
            System.out.println("Error : " + message);
            return ResponseEntity.status(500).body("Error : " + message);

        }
        String sendMail = mailSendService.sendEmailInvate(guestDTO);

        return ResponseEntity.ok("OK");
    }
}
