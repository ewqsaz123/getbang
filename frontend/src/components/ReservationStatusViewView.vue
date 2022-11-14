<template>

    <v-data-table
        :headers="headers"
        :items="reservationStatusView"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReservationStatusViewView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "reservationId", value: "reservationId" },
                { text: "reservationStatus", value: "reservationStatus" },
                { text: "reservationDate", value: "reservationDate" },
                { text: "scheduleId", value: "scheduleId" },
                { text: "roomId", value: "roomId" },
                { text: "roomName", value: "roomName" },
                { text: "cafeId", value: "cafeId" },
                { text: "cafeName", value: "cafeName" },
                { text: "userName", value: "userName" },
                { text: "userPhone", value: "userPhone" },
            ],
            reservationStatusView : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/reservationStatusViews'))

            temp.data._embedded.reservationStatusViews.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.reservationStatusView = temp.data._embedded.reservationStatusViews;
        },
        methods: {
        }
    }
</script>

