import React, { useState, useEffect } from 'react';
import ApplicationService from '../api/ApplicationService';
import ViewApplication from './ViewApplication';

const AllApplications = () => {

    const [applications, setApplications] = useState([{}]);
    let [toView, setToView] = useState({});
    const [view, setView] = useState(false);
    
    useEffect(() => {
        getAllApplications();
    }, [])

    const getAllApplications = async () => {
        const response = await ApplicationService.getAllApplications();
        setApplications(response.data)
    }

    const viewApplicationClicked = application => () => {
        setToView(application);
        setView(true);
    }

    const deleteApplicationClicked = applicationId => async () => {
        const response = await ApplicationService.deleteApplication(applicationId);
        getAllApplications();
    }

    return (
        <div className="container">
            <div className="py-5 text-center">
                <h1>Welcome</h1>
            </div>
            <div className="d-flex flex-row">
            <table className="table">
                <thead>
                    <tr>
                        <th>Application ID</th>
                        <th>Application Type</th>
                        <th>View</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                {applications.length>0
                && applications.map((application) => (
                    <tr>
                        <th>{application.applicationId}</th>
                        <td>
                            { application.applicationType==="home" && <span>Home Loan</span> }
                            { application.applicationType==="personal" && <span>Personal Loan</span> }
                            { application.applicationType==="business" && <span>Business Loan</span> }
                        </td>
                        <td>
                            <button 
                            className="btn btn-outline-info btn-sm"
                            type="button"
                            onClick={viewApplicationClicked(application)}
                            >
                            <span className="align-middle material-icons">visibility</span></button>
                        </td>
                        <td>
                            <button
                            className="btn btn-outline-info btn-sm"
                            type="button">
                            <span className="align-middle material-icons">mode_edit</span>
                            </button>
                        </td>
                        <td>
                        <button 
                className="btn btn-outline-primary btn-sm"
                type="button"
                // disabled={applicants.length<2}
                onClick={deleteApplicationClicked(application.applicationId)}
              ><span className="align-middle material-icons">delete</span></button>
                        </td>
                    </tr>
                    ))}
                </tbody>
            </table>
                </div>
                {view && <ViewApplication obj={toView} />}
        </div>
    )
}

export default AllApplications