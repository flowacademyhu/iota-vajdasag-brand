import React from 'react'
import { useTranslation } from 'react-i18next'

export default function EventsListHeader() {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        <th>{t('eventListHead.name')}</th>
        <th>{t('eventListHead.startTime')}</th>
        <th>{t('eventListHead.endTime')}</th>
        <th>{t('eventListHead.place')}</th>
      </tr>
    </thead>
  )
}
