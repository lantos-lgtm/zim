


Post: Type {
    author: Account,
    title: String,
    body: String,
    parent: Post
}

Person: Type {
    name: String,
    email: String,
    posts: Array {Post}
}

// example
// user.posts()
//  -> get User permisions -> get Group -> get group permisions -> get all posts with permisions


// permissions & accounts
Account: Type {
    details: Person,
    passwordHash: String,
    passwordSalt: String,
    childPermissions: Array {Permission},
    parentPermissions: Array {Permission},
    posts: Array {Post}
}
Group: Type {
    name: String,
    accounts: Array {Account},
    childPermissions: Array {Permission},
    parentPermissions: Array {Permission},
}

Permission: Type {
    ownerResourceType: String,
    ownerResourceId: String,
    resourceType: String,
    resourceId: String,
    resource: Resource,
    create: Boolean,
    read: Boolean,
    update: Boolean,
    delete: Boolean,
    execute: Boolean,
}

// permisions connector

