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
		euid = rand()%2000;
	}else{
		euid = rand()%2==0 ? ruid : suid;
	}
	printf("ruid=%d,euid=%d,suid=%d\n",ruid,euid,suid);
	return 0;
}
