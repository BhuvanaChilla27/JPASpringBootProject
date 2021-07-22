package com.example.demo.controllerl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("user")
	public String home()
	{
		return "user.jsp";
	}
	
	@DeleteMapping("/alien/{aid}")
	public String deletealien(@PathVariable int aid)
	{
		@SuppressWarnings("deprecation")
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return "deleted";
	}
	
	@PutMapping("/addAlien")
	public Alien saveOrupdatealien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
	@PostMapping("/addAlien")
	public Alien addalien(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
    @RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid)
	{
		return repo.findById(aid);
	}

	@GetMapping(path="aliens")
	public List<Alien> getAliens()
	{
		return repo.findAll();
	}
	/* @RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid)
	{
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		System.out.println((repo.findByTech("spring")));
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println(repo.findByAidLessThan(103));
		System.out.println(repo.findByTechSorted("spring"));
		mv.addObject(alien);
		return mv;
	}*/
}
