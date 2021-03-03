import React from 'react'
import { Formik, Form } from 'formik'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import eventAddValidation from '../validations/eventAddValidation'

const AddEventPage = () => {
  const { t } = useTranslation()

  const handleSubmit = () => {}

  return (
    <Formik
      initialValues={{
        name: '',
        bio: '',
        eventstart: '',
        eventend: '',
        place: '',
      }}
      validationSchema={eventAddValidation(t('registration.required'))}
      onSubmit={handleSubmit}
    >
      <Form>
        <div className="d-flex flex-column justify-content-center align-content-center mx-auto">
          <h3 className="text-center">{t('editProduct.title')}</h3>
          <Button variant="primary" type="submit" size="lg">
            {t('editProduct.save')}
          </Button>
        </div>
      </Form>
    </Formik>
  )
}

export default AddEventPage
