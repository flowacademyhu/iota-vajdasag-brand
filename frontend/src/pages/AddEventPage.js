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

  const handleSubmit = async (newEvent) => {
    console.log(moment(startDate).format('YYYY.MM.DD HH:MM'))
    console.log(moment(endDate).format('YYYY.MM.DD HH:MM'))
    try {
      await addEvent([...newEvent, { itemId: productId }])
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
            <h3 className="text-center">{t('addEvent.title')}</h3>
            <div className="my-2">
              <InputField label={t('addEvent.name')} name="name" type="text" />
            </div>
            <div className="my-2">
              <InputField label={t('addEvent.bio')} name="bio" type="text" />
            </div>
            <div className="my-2">
              <InputField
                label={t('addEvent.place')}
                name="place"
                type="text"
              />
              <DatePicker
                selected={startDate}
                onChange={(date) => setStartDate(date)}
                showTimeSelect
                timeFormat="HH:mm"
                timeIntervals={15}
                timeCaption="time"
                dateFormat="yyyy/MM/dd"
              />
              <DatePicker
                selected={endDate}
                onChange={(date) => setEndDate(date)}
                showTimeSelect
                timeFormat="yyyy.MM.dd"
                timeIntervals={15}
                timeCaption="time"
                dateFormat="yyyy.MM.dd "
              />
            </div>
            <Button variant="primary" type="submit" size="lg">
              {t('addEvent.save')}
            </Button>
          </div>
        </Form>
      </Formik>
    </>
  )
}

export default AddEventPage
