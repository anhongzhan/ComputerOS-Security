#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pwd.h>
#include <unistd.h>
#include <sys/types.h>

int change(char *name, char *text)
{
	uid_t uid = getuid();
	FILE *fp, *ft;
	char *strline = NULL;
	size_t len = 0;
	ssize_t read;
	long currentg, currentp;
	char *p = NULL;
	int samestr;
	char left[2048] = {0};

	fp = fopen("aaa","r+");
	if(uid)
		ft = fopen("123","w");
	else
	{
		setuid(1000);
		ft = fopen("123","w");
	}
	currentg = ftell(fp);//获取fp指定文件当前的读取位置
	currentp = ftell(ft);
	while((read = getline(&strline, &len, fp)) != -1)
	{
		p = strstr(strline,":");//“：”在strline中首次出现的位置
		if(p == NULL)
			continue;
//		printf("strline=%s",strline);
		samestr = strncmp(name, strline, p-strline);//比较name和strline前p->strline位的大小
		if(samestr == 0)
		{
			int c = 0;
			while(!feof(fp))
			{
				left[c++] = fgetc(fp);
			}
//			printf("c=%d\n",c);
			if(c)
				left[c-1] = '\0';
			fseek(ft, currentg, SEEK_SET);
			fprintf(ft, "%s:%s\n", name, text);
			fprintf(fp, "%s", text);
			if(c)
				fprintf(ft, "%s", left);
			fclose(fp);
			fclose(ft);
			remove("aaa");
			rename("123","aaa");
			if(uid)
			{
				FILE* f = fopen("123","w+");
				fclose(f);
			}else{
				setuid(1000);
				FILE *f = fopen("123","w+");
				fclose(f);
			}	
			return 0;
		}else{
			fprintf(ft, "%s", strline);
		}
		currentg = ftell(fp);
		currentp = ftell(ft);
	}
	return 1;
}

int main(int argc, char *argv[])
{
	struct passwd * user;
	uid_t uid = getuid();
	user = getpwuid(uid);

	printf("%s\n",user->pw_name);
	switch(argc)
	{
		case 2:
			change(user->pw_name, argv[1]);
			break;
		case 3:
			if(strcmp(user->pw_name,"root")==0)
				change(argv[1], argv[2]);
			break;
	}
	return 0;
}


