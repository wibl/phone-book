<script>
export default {
  name: 'home',
  computed: {
    records() {
      return this.$store.state.records
    }
  },
  methods: {
    onDeleteButtonClick (recordId) {
        this.$store.dispatch('deleteRecord', recordId)
    },
    onEditButtonClick (recordId) {
      this.$router.push({
        name: "edit",
        params: {
          recordId: recordId
        }
      })
    },
    onNewButtonClick () {
      this.$router.push({
        name: "edit",
        params: {
          recordId: -1
        }
      })
    },
  },
}
</script>

<template>
  <div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Name</th>
          <th>Number</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>            
          <tr v-for="record in records" v-bind:key="record.id">
            <td>
              {{ record.name }}
            </td>
            <td>
              {{ record.number }}
            </td>
            <td>
              <div class="btn-group">
                <button class="btn btn-secondary" v-on:click="onEditButtonClick(record.id)">Edit</button>
                <button class="btn btn-danger" v-on:click="onDeleteButtonClick(record.id)">Delete</button>
              </div>
            </td>
          </tr>
      </tbody>
    </table>
    <button class="btn btn-success" v-on:click="onNewButtonClick">New</button>
  </div>
</template>