import Vue from 'vue'
import Vuex from 'vuex'
import RecordService from '../services/index.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    records: [/*{
      id: 1, 
      name: "Petrov",
      number: "+79111111111"
    },
    {
      id: 2, 
      name: "Sidorov",
      number: "+79111111112"
    }*/]
  },
  mutations: {
    deleteRecord(state, id) {
      let recIdx = state.records.findIndex(r => r.id === id)
      if (recIdx !== -1) {
        state.records.splice(recIdx, 1)
      }
    },
    updateRecord(state, record) {
      let recIdx = state.records.findIndex(r => r.id === record.id)
      if (recIdx !== -1) {
        state.records[recIdx] = record
      }
    },
    addNewRecord(state, record) {
      state.records.push(record)
    }
  },
  actions: {
    deleteRecord ({ commit }, id) {
      commit('deleteRecord', id)
    },
    updateRecord ({ commit, state }, record) {
      if (record.id === -1) {
        let createdRecordId = state.records[state.records.length - 1].id + 1
        commit('addNewRecord', {id: createdRecordId, name: record.name, number: record.number})
      } else {
        commit('updateRecord', record)
      }
    },
    async loadRecords ({ commit }) {
      let {data} = await RecordService.getAllRecords()
      data.forEach(rec => {
        commit('addNewRecord', rec)        
      });
    },
  },
})