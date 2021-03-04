import React, { useState } from 'react'
import { Formik, Form } from 'formik'
import { useParams, useHistory } from 'react-router-dom'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import DatePicker from 'react-datepicker'
import 'react-datepicker/dist/react-datepicker.css'
import '../communications/userApi'
import InputField from '../components/InputField'
import eventAddValidation from '../validations/eventAddValidation'
import { addEvent } from '../communications/userApi'
import moment from 'moment'

const AddEventPage = () => {
  const { t } = useTranslation()
  const [startDate, setStartDate] = useState(new Date())
  const [endDate, setEndDate] = useState(new Date())
  const { productId } = useParams()
  let history = useHistory()

  const handleSubmit = async (eventData) => {
    try {
      const eventstart = moment(startDate).format('YYYY.MM.DD HH:MM:SS')
      const eventend = moment(endDate).format('YYYY.MM.DD HH:MM:SS')

      const newProgram = {
        ...eventData,
        eventstart,
        eventend,
        itemId: productId,
      }

      await addEvent(newProgram)
      history.push('/super-admin/items')
    } catch (e) {}
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
        validationSchema={eventAddValidation(t('registration.required'))}
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
              <h6 className="m-2">{t('eventList.startDate')}</h6>
              <DatePicker
                selected={startDate}
                onChange={(date) => setStartDate(date)}
                showTimeSelect
                timeFormat="HH:mm"
                timeIntervals={15}
                dateFormat="MMMM d, yyyy h:mm aa"
              />
              <h6 className="m-2">{t('eventList.endDate')}</h6>
              <DatePicker
                selected={endDate}
                onChange={(date) => setEndDate(date)}
                showTimeSelect
                timeFormat="HH:mm"
                timeIntervals={15}
                timeCaption="time"
                dateFormat="MMMM d, yyyy h:mm aa"
              />
            </div>
            <Button variant="primary" type="submit" size="lg" className="m-2">
              {t('eventList.save')}
            </Button>
          </div>
        </Form>
      </Formik>
    </>
  )
}

export default AddEventPage
