package com.sip.ams.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.entities.Candidat;

@Controller
public class HomeController {
	static ArrayList<Candidat> lc;
	static
	{
		lc= new ArrayList<Candidat>();
		Candidat c1 = new Candidat(1,"nawel","nawel@gmail.com","21212121");
		Candidat c2 = new Candidat(2,"sameh","sameh@gmail.com","44444121");
		Candidat c3 = new Candidat(3,"mohamed","mohamed@gmail.com","8888888888");
		lc.add(c1);
		lc.add(c2);
		lc.add(c3);
		
	}
	@RequestMapping("/index")
	public String  home()
	{
		return "home/index";
	}
	@RequestMapping("/candidats")
	public String listeCandidat(Model m)
	{
		//bch nesna3 tableau dobjet candidat

		String libeleFormation = "springBoot angular";
		m.addAttribute("lf", libeleFormation);
		m.addAttribute("tab", lc);
		return "home/candidats";
		
		
	}
	@GetMapping("/add")
	public String addCandidat()
	{
		return "home/add";
	}
	
	
	@PostMapping("/add")
	public String saveCandidat(	@RequestParam("id") int id,
								@RequestParam("nom") String nom,
								@RequestParam("email") String email, 
								@RequestParam("tel") String tel)
	
	{
		Candidat temp = new Candidat(id,nom,email,tel);
		lc.add(temp);
		//System.out.println("id+nom+email+tel");
		//return +id+nom+email+tel;
		return "redirect:candidats";
	}

	@GetMapping("/show/{idC}")
	@ResponseBody
	public String show(@PathVariable("idC")int id)
	{
		return "ID : "+id;
		//return"home/show";
	}
	
	@GetMapping("/delete/{idC}")
	public String delete(@PathVariable("idC")int id)
	{
		lc.remove(id);
		return "redirect:../candidats";
	}
}



