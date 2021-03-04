import React, { useState } from 'react'
import { Formik, Form } from 'formik'
import { useParams, useHistory } from 'react-router-dom'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
import ResponseModal from '../components/modals/ResponseModal'
import EventDatePicker from '../components/listofevents/EventDatePicker'
import eventAddValidation from '../validations/eventAddValidation'
import { addEvent } from '../communications/userApi'
import { formatDate } from '../eventHelpers'

const AddEventPage = () => {
  const [startDate, setStartDate] = useState(new Date())
  const [endDate, setEndDate] = useState(new Date())
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [isEventCreationSuccessful, setEventCreationSuccessful] = useState(
    false
  )
  const { t } = useTranslation()
  const { productId } = useParams()
  let history = useHistory()

  const handleSubmit = async (eventInfo) => {
    try {
      const newProgram = formatDate(startDate, endDate, eventInfo, productId)
      await addEvent(newProgram)
      setEventCreationSuccessful(true)
      setShowResponseModal(true)
    } catch (e) {
      setEventCreationSuccessful(false)
      setShowResponseModal(true)
    }
  }

  const responseModalTitle = isEventCreationSuccessful
    ? t('eventList.successful')
    : t('eventList.unsuccessful')

  const onClose = () => {
    setShowResponseModal(false)
    if (isEventCreationSuccessful) {
      history.push('/super-admin/items')
    }
  }

  return (
    <>
      <Formik
        initialValues={{
          name: '',
          bio: '',
          eventstart: '',
          eventend: '',
          place: '',
        }}
        validationSchema={eventAddValidation(
          t('eventList.required'),
          t('eventList.maxHundred'),
          t('eventList.maxThousand')
        )}
        onSubmit={handleSubmit}
      >
        <Form>
          <div className="d-flex flex-column justify-content-center align-content-center mx-auto">
            <h3 className="text-center">{t('eventList.title')}</h3>
            <div className="m-2">
              <InputField label={t('eventList.name')} name="name" type="text" />
            </div>
            <div className="m-2">
              <InputField label={t('eventList.bio')} name="bio" type="text" />
            </div>
            <div className="m-2">
              <InputField
                label={t('eventList.place')}
                name="place"
                type="text"
              />
            </div>
            <div className="m-2">
              <EventDatePicker
                title={t('eventList.startDate')}
                date={startDate}
                setDate={setStartDate}
              />
            </div>
            <div className="m-2">
              <EventDatePicker
                title={t('eventList.endDate')}
                date={endDate}
                setDate={setEndDate}
              />
            </div>
            <Button variant="primary" type="submit" size="lg" className="m-2">
              {t('eventList.save')}
            </Button>
          </div>
        </Form>
      </Formik>
      <ResponseModal
        onClose={onClose}
        showResponseModal={showResponseModal}
        title={responseModalTitle}
      />
    </>
  )
}

export default AddEventPage
