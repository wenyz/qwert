try {
    var db = connect('admin');
    if (db.system.users.find({'user': 'launchAdmin'}).count() == 0) {
        print('Ready to addUser launchAdmin');
        db.getSiblingDB("admin").createUser(
            {
                user: "launchAdmin",
                pwd: "launchAdmin",
                roles: [
                    {role: "userAdminAnyDatabase", db: "admin"}
                ]
            }
        )
    } else {
        print('Already exist launchAdmin.')
    }
    var db = connect('admin');
    if (db.system.users.find({'user': 'launchUser'}).count() == 0) {
        print('Ready to addUser launchAdmin');
        db.getSiblingDB("data").createUser(
            {
                user: "launchUser",
                pwd: "launchUser",
                roles: [
                    {role: "readWrite", db: "data"}
                ]
            }
        )
    } else {
        print('Already exist launchUser.')
    }
} catch (err) {
    print('Error occured:' + err);
}