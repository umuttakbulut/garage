# Vodafone-Garage

Vodafone-Garage-Case
Park Service:
TYPE: POST

URL: http://localhost:8080/vehicle/park

Body raw json: { "vehicleType": "Car", "colour": "Red", "plate": "06_EU_3406" }

output: Allocated for 1 slot

Leave Service:
TYPE: DELETE

URL: http://localhost:8080/vehicle/leave?key=1

output: Vehicle left

Status Service:
TYPE: GET

URL: http://localhost:8080/vehicle/status

output:

06_EU_3406 Red [1]

06_VG_4256 Black [3, 4, 5, 6]

34_GV_4578 Green [8, 9]

