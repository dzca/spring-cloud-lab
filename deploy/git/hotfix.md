https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging

```bash
$ git checkout master
Switched to branch 'master'

$ git checkout -b hotfix
Switched to a new branch 'hotfix'
$ vim index.html
$ git commit -a -m 'fixed the broken email address'
[hotfix 1fb7853] fixed the broken email address
 1 file changed, 2 insertions(+)

merge the hotfix branch back into your master branch to deploy to production.

$ git checkout master
$ git merge hotfix
Updating f42c576..3a0874c
Fast-forward
 index.html | 2 ++
 1 file changed, 2 insertions(+)
 
delete hotfix branch
$ git branch -d hotfix
Deleted branch hotfix (3a0874c).


```