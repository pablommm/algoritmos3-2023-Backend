import ar.edu.unsam.algo3.Backend.main.kotlin.Mail
import ar.edu.unsam.algo3.Backend.main.kotlin.MailSender
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.mockk

class MailSender : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val mockMailSender = mockk<MailSender>()



    it("prueba de mail") {
        val mail = Mail("from", "to", "subject", "content")
        val mailSender = ar.edu.unsam.algo3.Backend.main.kotlin.MailSender()
        //mailSender.sendMail(mail)
    }
})

