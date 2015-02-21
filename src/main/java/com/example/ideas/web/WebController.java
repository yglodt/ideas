package com.example.ideas.web;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ideas.dao.IdeaDao;
import com.example.ideas.dao.VoteDao;
import com.example.ideas.domain.Idea;
import com.example.ideas.domain.Vote;

@Controller
@EnableAutoConfiguration
public class WebController {

	final static Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	private IdeaDao ideaDao;

	@Autowired
	private VoteDao voteDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndex(final Model model) {
		model.addAttribute("ideas", ideaDao.findAll());
		return "index";
	}

	@RequestMapping(value = "/idea/{id}", method = RequestMethod.GET)
	public String getIdea(final Model model, @PathVariable("id") UUID id) {

		Idea idea = ideaDao.findOne(id);

		Idea comment = new Idea();
		comment.setParent(idea);

		model.addAttribute("idea", idea);
		model.addAttribute("comment", comment);

		// ideaDao.findByName("abc");

		model.addAttribute("comments", ideaDao.findByParent(idea));
		return "idea";
	}

	@RequestMapping(value = "/idea/edit", method = RequestMethod.GET)
	public String getEditIdea(Model model,
			@RequestParam(value = "id", required = false) final UUID id) {

		if (id == null) {
			model.addAttribute("idea", new Idea());
		} else {
			model.addAttribute("idea", ideaDao.findOne(id));
		}

		return "ideaform";
	}

	@RequestMapping(value = "/idea/edit", params = "save", method = RequestMethod.POST)
	public String postIdea(@ModelAttribute final Idea idea,
			final BindingResult result) {

		// ValidationUtils.rejectIfEmptyOrWhitespace(result, "name", "name");
		// ValidationUtils.rejectIfEmptyOrWhitespace(result, "description",
		// "description");

		if (result.hasErrors()) {
			logger.error("Error inserting");
			// Utils.logValidationErrors(result);
			return "ideaform";
		} else {

			idea.setDateCreated(new Date());
			ideaDao.save(idea);

			return "redirect:/";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public boolean postVote(@RequestParam(value = "id") final String ideaId) {

		Vote vote = new Vote();
		vote.setDateCreated(new Date());
		vote.setIdea(new Idea(UUID.fromString(ideaId)));
		voteDao.save(vote);

		return true;
	}

	@InitBinder
	private void initBinder(final HttpServletRequest request,
			final ServletRequestDataBinder binder) throws Exception {

		// binder.registerCustomEditor(Date.class, new CustomDateEditor(new
		// SimpleDateFormat(smartHome.getBuilding().getDateFormat()), true));

		binder.registerCustomEditor(Idea.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(final String text) {
				setValue(StringUtils.isEmpty(text) ? null : new Idea(UUID
						.fromString(text)));
			}

			@Override
			public String getAsText() {
				final Idea idea = (Idea) getValue();
				return idea != null && idea.getId() != null ? idea.getId()
						.toString() : null;
			}
		});

	}

}
