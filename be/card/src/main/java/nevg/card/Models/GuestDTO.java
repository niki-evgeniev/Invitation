package nevg.card.Models;

import nevg.card.Models.Entity.Followers;

import java.util.List;

public class GuestDTO {
    private String email;
    private String mainFirstName;
    private String mainLastName;
    private String message;
    private List<FollowerDTO> guests;

    public GuestDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FollowerDTO> getGuests() {
        return guests;
    }

    public void setGuests(List<FollowerDTO> guests) {
        this.guests = guests;
    }

    public String getMainFirstName() {
        return mainFirstName;
    }

    public void setMainFirstName(String mainFirstName) {
        this.mainFirstName = mainFirstName;
    }

    public String getMainLastName() {
        return mainLastName;
    }

    public void setMainLastName(String mainLastName) {
        this.mainLastName = mainLastName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
