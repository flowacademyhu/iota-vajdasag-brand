import { Formik, Form } from 'formik'
import { Button } from 'react-bootstrap'
import InputField from './InputField'

const forgottenPasswordForm = ({
  email,
  title,
  invalidemail,
  noEmail,
  buttontext,
  handleSubmit,
  ForgettenPasswordSchema,
}) => (
  <Formik
    initialValues={{
      email: '',
    }}
    onSubmit={handleSubmit}
    validationSchema={ForgettenPasswordSchema(invalidemail, noEmail)}
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
        ></InputField>
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
