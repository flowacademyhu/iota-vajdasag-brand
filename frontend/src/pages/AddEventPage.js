import React from 'react'
import { Formik, Form } from 'formik'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
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
          <h3 className="text-center">{t('addEvent.title')}</h3>
          <div className="my-2">
            <InputField label={t('addEvent.name')} name="name" type="text" />
          </div>
          <div className="my-2">
            <InputField label={t('addEvent.bio')} name="bio" type="text" />
          </div>
          <div className="my-2">
            <InputField label={t('addEvent.place')} name="place" type="text" />
          </div>
          <Button variant="primary" type="submit" size="lg">
            {t('addEvent.save')}
          </Button>
        </div>
      </Form>
    </Formik>
  )
}

export default AddEventPage
