import moment from 'moment'

const formatDate = (startDate, endDate, eventInfo, productId) => {
  const eventstart = moment(startDate).format('YYYY.MM.DD hh:mm:ss')
  const eventend = moment(endDate).format('YYYY.MM.DD hh:mm:ss')

  const newProgram = {
    ...eventInfo,
    eventstart,
    eventend,
    itemId: productId,
  }

  return newProgram
}

export { formatDate }
