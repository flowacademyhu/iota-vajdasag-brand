import { Formik, Form } from 'formik'
import { Button } from 'react-bootstrap'
import InputField from './InputField'
import forgottenPasswordShema from '../validations/ForgottenPasswordShema'



const forgottenPasswordForm = ({
  email,
  title,
  invalidemail,
  noEmail,
  buttontext,
  handleSubmit,
}) => (
  <Formik
    initialValues={{
      email: '',
    }}
    onSubmit={handleSubmit}
    validationSchema={forgottenPasswordShema(invalidemail, noEmail)}
  >
    <Form>
      <h3 className="text-center">{title}</h3>
      <div className="my-3">
        <InputField
          label={email}
          name="email"
          id="email"
          placeholder={email}
          type="email"
        />
      </div>
      <div className="text-center">
        <Button variant="primary" type="submit">
          {buttontext}
        </Button>
      </div>
    </Form>
  </Formik>
)

export default forgottenPasswordForm
