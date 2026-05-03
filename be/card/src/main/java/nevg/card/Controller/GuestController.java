package nevg.card.Controller;


import nevg.card.Models.Entity.Guest;
import nevg.card.Repository.FollowersRepository;
import nevg.card.Repository.GuestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GuestController {

    private final GuestRepository guestRepository;
    private final FollowersRepository followersRepository;

    public GuestController(GuestRepository guestRepository, FollowersRepository followersRepository) {
        this.guestRepository = guestRepository;
        this.followersRepository = followersRepository;
    }

    @GetMapping("/guests")
    public ModelAndView guests() {
        ModelAndView modelAndView = new ModelAndView("guest");

        List<Guest> guests = guestRepository.findAllWithFollowers();
        long guestCount = guestRepository.count();
        long followersCount = followersRepository.count();
        long totalGuestCounter = guestCount + followersCount;
        modelAndView.addObject("guests", guests);
        modelAndView.addObject("countGuest", totalGuestCounter);
        return modelAndView;
    }
}
