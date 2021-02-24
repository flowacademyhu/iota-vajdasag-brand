import React from 'react'
import { Field } from 'formik'
import { useTranslation } from 'react-i18next'

const SelectCategory = () => {
  const { t } = useTranslation()
  return (
    <>
      <label htmlFor="location">{t('editProduct.selectCategory')}</label>
      <Field component="select" id="location" name="location">
        <option value="ATTRACTION">{t('editProduct.selectAttraction')}</option>
        <option value="GASTRONOMY">{t('editProduct.selectGastronomy')}</option>
        <option value="HOTEL">{t('editProduct.selectHotel')}</option>
        <option value="HOLIDAY">{t('editProduct.selectHoliday')}</option>
        <option value="INFORMATION">
          {t('editProduct.selectInformation')}
        </option>
      </Field>
    </>
  )
}

export default SelectCategory
