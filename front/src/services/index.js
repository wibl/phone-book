import axios from 'axios'

class RecordService {
    getAllRecords() {
        return axios.get('/phones')
    }    
}

 
export default new RecordService()