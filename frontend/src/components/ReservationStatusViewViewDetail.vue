<template>

    <v-card outlined>
        <v-card-title>
            ReservationStatusView # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="ReservationId" v-model="item.reservationId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReservationStatus" v-model="item.reservationStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="ReservationDate" v-model="item.reservationDate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="ScheduleId" v-model="item.scheduleId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="RoomId" v-model="item.roomId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="RoomName" v-model="item.roomName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="CafeId" v-model="item.cafeId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CafeName" v-model="item.cafeName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="UserName" v-model="item.userName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="UserPhone" v-model="item.userPhone" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ReservationStatusViewViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/reservationStatusViews/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

