package nevg.card.Controller;


import nevg.card.Models.Entity.Guest;
import nevg.card.Repository.GuestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GuestController {

    private final GuestRepository guestRepository;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping("/guests")
    public ModelAndView guests() {
        ModelAndView modelAndView = new ModelAndView("guest");

        List<Guest> guests = guestRepository.findAllWithFollowers();

        System.out.println(guests);

        modelAndView.addObject("guests", guests);
        return modelAndView;
    }
}
