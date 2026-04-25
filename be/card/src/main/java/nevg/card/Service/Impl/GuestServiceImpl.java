package nevg.card.Service.Impl;

import nevg.card.Models.Entity.Followers;
import nevg.card.Models.Entity.Guest;
import nevg.card.Models.FollowerDTO;
import nevg.card.Models.GuestDTO;
import nevg.card.Repository.FollowersRepository;
import nevg.card.Repository.GuestRepository;
import nevg.card.Service.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final FollowersRepository followersRepository;

    public GuestServiceImpl(GuestRepository guestRepository, FollowersRepository followersRepository) {
        this.guestRepository = guestRepository;
        this.followersRepository = followersRepository;
    }

    @Override
    public String addGuest(GuestDTO guestDTO) {

        String email = guestDTO.getEmail();
        Optional<Guest> byEmail = guestRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            Guest guest = new Guest();
            guest.setEmail(email);
            guest.setName(guestDTO.getMainFirstName());
            guest.setLastName(guestDTO.getMainLastName());
            if (guestDTO.getMessage() != null) {
                System.out.println("Add Messages : " + guestDTO.getMessage());
                guest.setMessage(guestDTO.getMessage());
            }
            System.out.println();
            guestRepository.save(guest);
            System.out.println("Guest Successful Added With Name : "
                    + guestDTO.getMainFirstName() + " " + guestDTO.getMainLastName());
            if (guestDTO.getGuests() != null && !guestDTO.getGuests().isEmpty()) {
                List<FollowerDTO> guests = guestDTO.getGuests();
                for (FollowerDTO follower : guests) {
                    Followers followers = new Followers();
                    followers.setName(follower.getFirstName());
                    followers.setLastName(follower.getLastName());
                    followers.setGuest(guest);
                    followersRepository.save(followers);
                }
                System.out.println("Add Followers for user " + guestDTO.getMainFirstName() +
                        " " + guestDTO.getMainLastName());
            }
            if (guestDTO.getGuests() != null) {
                System.out.println("Followers number is : " + guestDTO.getGuests().size());
            }

            return "Successful";
        }
        System.out.println("Guest Email Address exist " + guestDTO.getEmail());
        return "Email exist";
    }
}
