package Controllers

import controllers.{ProfileController, SignInController}
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.mvc.Result
import play.api.test._
import play.api.test.Helpers._
import services.PersonService

import scala.concurrent.Future

class ProfileControllerSpec extends PlaySpec with OneAppPerTest with MockitoSugar {
  "Sing In Page" should {
    "should render the sign in page" in {
      val person = mock[PersonService]
//      val singincontroller = new SignInController(person)
      val profilecontroller = new ProfileController(person)
//      val result:Future[Result] = singincontroller.signIn().apply(FakeRequest())
      val result:Future[Result] = profilecontroller.profile().apply(FakeRequest())
      val bodyText:String = contentAsString(result)
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }
}
