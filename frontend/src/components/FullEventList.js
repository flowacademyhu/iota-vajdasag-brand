import React, { useState, useEffect } from 'react'
import { getAllEvents } from '../communications/userApi'
import ListElements from "../components/listofevents/ListElements";

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
        <div className="table-responsive">
            <table className="table table-striped table-sm">
                <tbody>
                    {events?.map(event => (
                        <ListElements event={event} />
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default FullEventList;