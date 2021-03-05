import React from 'react'
import 'react-datepicker/dist/react-datepicker.css'
import DatePicker, { registerLocale } from 'react-datepicker'
import hu from 'date-fns/locale/hu'

const EventDatePicker = ({ title, date, setDate }) => {
  registerLocale('hu', hu)
  return (
    <>
      <h6 className="m-2"> {title}</h6>
      <DatePicker
        selected={date}
        onChange={(time) => setDate(time)}
        locale="hu"
        showTimeSelect
        timeIntervals={15}
        timeCaption="time"
        dateFormat="yyyy MMMM d, h:mm aa"
      />
    </>
  )
}

export default EventDatePicker
