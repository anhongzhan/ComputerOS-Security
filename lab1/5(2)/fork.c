#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <wait.h>

#define _GNU_SOURCE

void showIP();

int main()
{
	pid_t pid = fork();


	if(pid==0){
		showIP();
	}else{
		showIP();
	}
	wait(NULL);
}

void showIP()
{
	uid_t ruid,euid,suid;
	if(getresuid(&ruid,&euid,&suid)>=0)
		printf("ruid=%d,euid=%d,suid=%d\n",ruid,euid,suid);
}
