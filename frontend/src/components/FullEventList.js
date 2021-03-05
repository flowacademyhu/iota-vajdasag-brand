import React, { useState, useEffect } from 'react'
import { getAllEvents, getEventsById } from '../communications/userApi'
import ListElements from '../components/listofevents/ListElements'
import EventsListHeader from '../components/listofevents/EventsListHeader'
import { useLocation } from 'react-router-dom'
import { useTranslation } from 'react-i18next'

const FullEventList = (props) => {
  const { t } = useTranslation()
  const [events, setEvents] = useState()
  const [error, setError] = useState()
  const location = useLocation()

  const getEvents = async () => {
    const productId = location.state
    try {
      if (!productId) {
        const response = await getAllEvents()
        setEvents(response.data.content)
      } else {
        const response = await getEventsById(productId)
        setEvents(response.data.content)
      }
    } catch (error) {
      setError(error)
    }
  }

  useEffect(() => {
    getEvents()
  })

  return (
    <div className="table-responsive">
      <table className="table table-striped table-sm">
        <EventsListHeader />
        <tbody>
          {events?.map((event) => (
            <ListElements event={event} />
          ))}
          {error && <h5>{t('eventList.error')}</h5>}
        </tbody>
      </table>
    </div>
  )
}

export default FullEventList
