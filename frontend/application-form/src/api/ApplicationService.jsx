import axios from 'axios';

class ApplicationService {

    getAllApplications() {
        return axios.get(`http://localhost:8080/applications/`)
    }

    getApplication(applicationId) {
        return axios.get(`http://localhost:8080/applications/${applicationId}`)
    }

    submitApplication(application) {
        return axios.post(`http://localhost:8080/applications/`, application)
    }

    deleteApplication(applicationId) {
        return axios.delete(`http://localhost:8080/applications/${applicationId}`)
    }
}

export default new ApplicationService()