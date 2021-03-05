import React from 'react'
import { Button} from 'react-bootstrap'
import { useTranslation } from 'react-i18next'
import { Link } from 'react-router-dom'

const EventsProductButton = ({ productId }) => {
  const { t } = useTranslation()

  return (
    <>
      <Link
        to={{
          pathname: '/super-admin/events',
          state: productId,
        }}
      >
        {console.log('productId', productId)}
        <Button>{t('eventList.button')}</Button>
      </Link>
    </>
  )
}

export default EventsProductButton
