import React from 'react'
import { useTranslation } from 'react-i18next'
import { Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'

const AddEventButton = ({ productId }) => {
  const { t } = useTranslation()

  return (
    <Link to={`/super-admin/add-event/${productId}`}>
      <Button variant="success"> {t('eventList.addEvent')}</Button>
    </Link>
  )
}

export default AddEventButton
