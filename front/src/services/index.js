import axios from 'axios'

class RecordService {
    
    getAllRecords() {
        return axios.get('/phones')
    }

    addNewRecord(record) {
        return axios.post('/phones', record)
    }
    
    updateRecord(record) {
        return axios.put('/phones', record)
    }

    deleteRecord(id) {
        return axios.delete('/phones/' + id)
    }
}
 
export default new RecordService()