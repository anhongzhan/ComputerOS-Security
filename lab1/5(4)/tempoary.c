#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>

#define _GNU_SOURCE

int main()
{
	uid_t ruid,euid,suid;
	getresuid(&ruid,&euid,&suid);
	if(euid==0){
		ruid = euid;
		suid = euid;
	}else{
		euid = ruid;
		suid = ruid;
	}
	printf("ruid=%d,euid=%d,suid=%d\n",ruid,euid,suid);
	return 0;
}
