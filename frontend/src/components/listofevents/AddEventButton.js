import React from 'react'
import { useTranslation } from 'react-i18next'
import { Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'

const AddEventButton = () => {
  const { t } = useTranslation()

  return (
    <Link to={`/events/add-event`}>
      <Button> {t('eventList.addEvent')}</Button>
    </Link>
  )
}

export default AddEventButton
