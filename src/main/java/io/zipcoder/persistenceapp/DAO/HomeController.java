package io.zipcoder.persistenceapp.DAO;


import io.zipcoder.persistenceapp.Home;
import io.zipcoder.persistenceapp.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/homes")
@RestController
public class HomeController {
    HomeService homeService;

    @Autowired
    public HomeController(HomeService h){
        homeService=h;
    }

    @GetMapping
    public List<Home> neighborhood(){return homeService.list();}

    @GetMapping(path="byid/{id}")
    public Home getHomeById(@PathVariable int id){return homeService.get(id).get();}

    @GetMapping(path="byphone/{num}")
    public Home getHomeByHumeNumber(@PathVariable String num){return homeService.getByHomeNumber(num).get();}

    //@GetMapping(path="/byaddress/{address}")
    //public Home getHomeByAddress(@PathVariable String address){return homeService.getByAddress(address).get();}
    @GetMapping(path="bypersonid/{id}")
    public Home getHomeByPersonId(@PathVariable int id){return homeService.getByPersonId(id).get();}


    @PostMapping
    public void newHome(@RequestBody Home h){ homeService.create(h);}

    @PutMapping(path="{id}")
    public void updateHome(@RequestBody Home h, @PathVariable int id){homeService.update(h,id);}

    @DeleteMapping(path="{id}")
    public void deleteHouseById(@PathVariable int id){homeService.delete(id);}

}
