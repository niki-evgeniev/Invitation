package nevg.card.Service;

import nevg.card.Models.GuestDTO;

public interface MailSendService {
    String sendEmailInvate(GuestDTO guestDTO);
}
