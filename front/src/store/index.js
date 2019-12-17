import Vue from 'vue'
import Vuex from 'vuex'
import RecordService from '../services/index.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    records: []
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
    async deleteRecord({ commit }, id) {
      await RecordService.deleteRecord(id)
      commit('deleteRecord', id)
    },
    async updateRecord({ commit, state }, record) {
      if (record.id === -1) {
        let { data } = await RecordService.addNewRecord(record)
        commit('addNewRecord', data)
      } else {
        await RecordService.updateRecord(record)
        commit('updateRecord', record)
      }
    },
    async loadRecords({ commit }) {
      let { data } = await RecordService.getAllRecords()
      data.forEach(rec => {
        commit('addNewRecord', rec)        
      });
    },
  },
})