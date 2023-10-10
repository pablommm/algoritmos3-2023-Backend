package ar.edu.unsam.algo3.Backend.main.kotlin

interface MailSender {
    fun sendMail(mail: Mail)

}
data class Mail(val from: String, val to: String, val subject: String, val content: String)
