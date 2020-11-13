package ru.study.hello.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import ru.study.hello.model.User
import ru.study.hello.service.UserService

@Controller
class UserController(
        private var userService: UserService
) {

    @RequestMapping(value = ["/", "/login"], method = [RequestMethod.GET])
    fun login(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "login"
        return modelAndView
    }

    @RequestMapping(value = ["/registration"], method = [RequestMethod.GET])
    fun registration(): ModelAndView {
        val modelAndView = ModelAndView()
        val user = User()
        modelAndView.addObject("user", user)
        modelAndView.viewName = "registration"
        return modelAndView
    }

    @RequestMapping(value = ["/registration"], method = [RequestMethod.POST])
    fun createNewUser(user: User, bindingResult: BindingResult): ModelAndView {
        val modelAndView = ModelAndView()
        val userExists = userService.findUserByEmail(user.email)
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user",
                    "There is already a user registered with the email provided")
        }
        if (!bindingResult.hasErrors()) {
            userService.saveUser(user)
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", User());
        }
        modelAndView.viewName = "registration"
        return modelAndView
    }

    @RequestMapping(value = ["/admin/adminHome"], method = [RequestMethod.GET])
    fun home(): ModelAndView {
        val modelAndView = ModelAndView()
        val authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findUserByEmail(authentication.name)
        modelAndView.apply {
            addObject("messageDay", "Welcome ${user?.name} ${user?.lastName} (${user?.email})")
            addObject("adminMessage", "This Page is available to Users with Admin Role")
            viewName = "admin/adminHome"
        }
        return modelAndView
    }

    @RequestMapping(value = ["/user/userHome"], method = [RequestMethod.GET])
    fun user(): ModelAndView {
        val modelAndView = ModelAndView()
        val authentication = SecurityContextHolder.getContext().authentication
        val user = userService.findUserByEmail(authentication.name)
        modelAndView.apply {
            addObject("userMessage", "Welcome ${user?.name} ${user?.lastName} (${user?.email})")
            addObject("userMessage", "This Page is available to Users with User Role")
            viewName = "user/userHome"
        }
        return modelAndView
    }
}