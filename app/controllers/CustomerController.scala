package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import play.api.data._
import play.api.data.Forms._
import repository.CustomerRepository

@Singleton
class CustomerController @Inject()(repo: CustomerRepository, cc: MessagesControllerComponents)
                                  (implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  // Defines the form outside of the methods so that it is accessible throughout the class.
  val customerForm: Form[CustomerForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "age" -> number
        .verifying("Age must be greater than or equal to 1", age => age >= 1)
        .verifying("Age must be less than or equal to 100", age => age <= 100)
    )(CustomerForm.apply)(CustomerForm.unapply)
  )

  def index(): Action[AnyContent] = Action { implicit request: MessagesRequest[AnyContent] =>
    // Pass the form as a value when rendering the view.
    Ok(views.html.index(customerForm))
  }

  def addCustomerData(): Action[AnyContent] = Action.async { implicit request =>
    customerForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(BadRequest(views.html.index(errorForm)))
      },
      successForm => {
        repo.insert(successForm.name, successForm.age).map { _ =>
          Redirect(routes.CustomerController.index()).flashing("success" -> "user.created")
        }
      }
    )
  }

  def getCustomerData: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    repo.list().map{ seqOfCustomer =>
      Ok(views.html.customerData(seqOfCustomer))
    }
  }
}

// Defines the case class outside the controller class.
case class CustomerForm(name: String, age: Int)
