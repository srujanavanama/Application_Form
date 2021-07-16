import React from 'react';

const ViewApplication = ({obj}) => {
    const applicationID = obj.applicationId;
    const applicationType = obj.applicationType;
    const amount = obj.amount;
    const applicationStatus = obj.applicationStatus;
    const applicants = obj.applicants;
    return (
        <div className="container">
            <div className="py-5 text-center">
                {/* <h4 className="d-flex justify-content-between align-items-center mb-3 text-center"> */}
                <h2>Application Details</h2>
                <ul className="list-group mb-3">
                    <li className="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 className="my-0">Application ID</h6>
                            {/* <small className="text-muted">Amount</small> */}
                        </div>
                        <span>{applicationID}</span>
                    </li>
                    <li className="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 className="my-0">Application Type</h6>
                            {/* <small className="text-muted">Home/Personal/Business</small> */}
                        </div>
                        { applicationType==="home" && <span>Home Loan</span> }
                        { applicationType==="personal" && <span>Personal Loan</span> }
                        { applicationType==="business" && <span>Business Loan</span> }
                    </li>
                    <li className="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 className="my-0">Amount</h6>
                            {/* <small className="text-muted">Amount</small> */}
                        </div>
                        <span>{amount}</span>
                    </li>
                    <li className="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 className="my-0">Application Status</h6>
                            {/* <small className="text-muted">Active/Inactive</small> */}
                        </div>
                        { applicationStatus==="active" && <span>Active</span> }
                        { applicationStatus==="inactive" && <span>In Active</span> }
                    </li>
                    {applicants && applicants.map((applicant, applicantId) => (
                        <div key={applicant.applicantId}>
                        <br />
                        <h6>Applicant {applicantId + 1}</h6>
                            <li className="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 className="my-0">First Name</h6>
                                    {/* <small className="text-muted">First Name of Applicant {applicantId + 1}</small> */}
                                </div>
                                <span>{applicant.firstName}</span>
                            </li>
                            <li className="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 className="my-0">Last Name</h6>
                                    {/* <small className="text-muted">Last Name of Applicant {applicantId + 1}</small> */}
                                </div>
                                <span>{applicant.lastName}</span>
                            </li>
                            <li className="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 className="my-0">Gender</h6>
                                    {/* <small className="text-muted">Male/Female/NotDisclosed</small> */}
                                </div>
                                { applicant.gender==="male" && <span>Male</span> }
                                { applicant.gender==="female" && <span>Female</span> }
                                { applicant.gender==="na" && <span>Not Disclosed</span> }
                            </li>
                            <li className="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 className="my-0">Applicant ID Type</h6>
                                    {/* <small className="text-muted"></small> */}
                                </div>
                                <ul className="list-inline">
                                {applicant.applicantIdTypes.map((idtype, idx) => (
                                    <li key={idx} className="list-inline-item d-flex justify-content-between lh-condensed">
                                    <span>{idtype}</span>
                                    </li>
                                ))}
                                </ul>
                            </li>
                        </div>
                    ))}
                </ul>
            </div>
        </div>
    )
}

export default ViewApplication;