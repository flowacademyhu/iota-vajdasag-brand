import React, { useState, useEffect } from 'react'
import { getAllEvents } from '../communications/userApi'

const FullEventList = () => {
    const [events, setEvents] = useState()
    const [error, setError] = useState()

    const getEvents = async () => {
        try {
            const response = await getAllEvents()
            setEvents(response.data.content)
        } catch (error) {
            setError(error)
        }
    }

    useEffect(() => {
        getEvents()
    }, [])


    return (
        <>
        { events?.map(event=> (
            <h1>{event.name}</h1>
        ))}
            
        </>
    );
}

export default FullEventList;